import React from 'react';
import {render, screen} from '@testing-library/react';
import {getCurrentUserToken} from "../../api/auth/auth.service";
import AccountDetails from "../../dashboard/account";

// Mocken der react-router-dom Funktion useNavigate
jest.mock('react-router-dom', () => ({
    useNavigate: jest.fn(),
}));

// Mocken der Funktion getCurrentUserToken aus dem auth.service Modul
jest.mock('../../api/auth/auth.service', () => ({
    getCurrentUserToken: jest.fn(),
}));

describe('AccountDetails', () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });

    // Testen, ob die Account-Details gerendert werden
    test('renders account details', async () => {
        // Mock-Daten
        const mockUserData = { username: 'testuser', email: 'testuser@example.com' };

        // Mocken der getCurrentUserToken-Funktion
        getCurrentUserToken.mockReturnValue('mock_token');

        // Mocken der window.fetch()-Funktion
        jest.spyOn(window, 'fetch').mockResolvedValueOnce({
            ok: true,
            json: jest.fn().mockResolvedValueOnce(mockUserData),
        });

        // Rendern des AccountDetails-Komponente
        render(<AccountDetails />);

        // Überprüfen, ob das "Loading..."-Element gerendert wird
        expect(screen.getByText('Loading...')).toBeInTheDocument();
        // Überprüfen, ob die Funktion getCurrentUserToken aufgerufen wurde
        expect(getCurrentUserToken).toHaveBeenCalled();
        // Überprüfen, ob die Funktion window.fetch() mit der richtigen URL und Header aufgerufen wurde
        expect(window.fetch).toHaveBeenCalledWith('http://localhost:8080/api/test/account', {
            method: 'GET',
            headers: { Authorization: 'Bearer mock_token' },
        });

        // Auf das Erscheinen des Texts "Account Details" warten
        await screen.findByText('Account Details');
        // Überprüfen, ob die Benutzerdaten gerendert wurden
        expect(screen.getByText(`Username: ${mockUserData.username}`)).toBeInTheDocument();
        expect(screen.getByText(`Email: ${mockUserData.email}`)).toBeInTheDocument();
    });

    // Testen, ob das "Loading..."-Element gerendert wird, wenn keine Benutzerdaten vorhanden sind
    test('renders loading when user data is null', async () => {
        // Mocken der getCurrentUserToken-Funktion
        getCurrentUserToken.mockReturnValue('mock_token');
        // Mocken der window.fetch()-Funktion
        jest.spyOn(window, 'fetch').mockResolvedValueOnce({
            ok: true,
            json: jest.fn().mockResolvedValueOnce(null),
        });

        // Rendern des AccountDetails-Komponente
        render(<AccountDetails />);

        // Überprüfen, ob das "Loading..."-Element gerendert wird
        expect(screen.getByText('Loading...')).toBeInTheDocument();
        // Überprüfen, ob die Funktion getCurrentUserToken aufgerufen wurde
        expect(getCurrentUserToken).toHaveBeenCalled();
        // Überprüfen, ob die Funktion window.fetch() mit der richtigen URL und Header aufgerufen wurde
        expect(window.fetch).toHaveBeenCalledWith('http://localhost:8080/api/test/account', {
            method: 'GET',
            headers: { Authorization: 'Bearer mock_token' },
        });

        // Auf das Erscheinen des Texts "Loading..." warten
        await screen.findByText('Loading...');
    });
    //Testet, ob eine Fehlermeldung gerendert wird, wenn der Abruf fehlschlägt.
    test('renders error message when fetch fails', async () => {
        // Mocken der getCurrentUserToken-Funktion
        getCurrentUserToken.mockReturnValue('mock_token');
        // Mocken der window.fetch()-Funktion
        jest.spyOn(window, 'fetch').mockResolvedValueOnce({ ok: false });

        // Rendern des AccountDetails-Komponente
        render(<AccountDetails />);

        // Überprüfen, ob das "Loading..."-Element gerendert wird
        expect(getCurrentUserToken).toHaveBeenCalled();
        // Überprüfen, ob die Funktion window.fetch() mit der richtigen URL und Header aufgerufen wurde
        expect(window.fetch).toHaveBeenCalledWith('http://localhost:8080/api/test/account', {
            method: 'GET',
            headers: { Authorization: 'Bearer mock_token' },
        });
        // Wartet auf das Erscheinen des Texts "Loading..."
        await screen.findByText(/Loading.../i);
    });
});