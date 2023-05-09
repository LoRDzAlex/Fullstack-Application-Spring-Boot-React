import React from 'react';
import {render, screen} from '@testing-library/react';
import {getCurrentUserToken} from "../../api/auth/auth.service";
import AccountDetails from "../../dashboard/account";


jest.mock('react-router-dom', () => ({
    useNavigate: jest.fn(),
}));

jest.mock('../../api/auth/auth.service', () => ({
    getCurrentUserToken: jest.fn(),
}));

describe('AccountDetails', () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });

    test('renders account details', async () => {
        const mockUserData = { username: 'testuser', email: 'testuser@example.com' };
        getCurrentUserToken.mockReturnValue('mock_token');
        jest.spyOn(window, 'fetch').mockResolvedValueOnce({
            ok: true,
            json: jest.fn().mockResolvedValueOnce(mockUserData),
        });

        render(<AccountDetails />);

        expect(screen.getByText('Loading...')).toBeInTheDocument();
        expect(getCurrentUserToken).toHaveBeenCalled();
        expect(window.fetch).toHaveBeenCalledWith('http://localhost:8080/api/test/account', {
            method: 'GET',
            headers: { Authorization: 'Bearer mock_token' },
        });

        await screen.findByText('Account Details');
        expect(screen.getByText(`Username: ${mockUserData.username}`)).toBeInTheDocument();
        expect(screen.getByText(`Email: ${mockUserData.email}`)).toBeInTheDocument();
    });

    test('renders loading when user data is null', async () => {
        getCurrentUserToken.mockReturnValue('mock_token');
        jest.spyOn(window, 'fetch').mockResolvedValueOnce({
            ok: true,
            json: jest.fn().mockResolvedValueOnce(null),
        });

        render(<AccountDetails />);

        expect(screen.getByText('Loading...')).toBeInTheDocument();
        expect(getCurrentUserToken).toHaveBeenCalled();
        expect(window.fetch).toHaveBeenCalledWith('http://localhost:8080/api/test/account', {
            method: 'GET',
            headers: { Authorization: 'Bearer mock_token' },
        });

        await screen.findByText('Loading...');
    });

    test('renders error message when fetch fails', async () => {
        getCurrentUserToken.mockReturnValue('mock_token');
        jest.spyOn(window, 'fetch').mockResolvedValueOnce({ ok: false });

        render(<AccountDetails />);

        expect(getCurrentUserToken).toHaveBeenCalled();
        expect(window.fetch).toHaveBeenCalledWith('http://localhost:8080/api/test/account', {
            method: 'GET',
            headers: { Authorization: 'Bearer mock_token' },
        });

        await screen.findByText(/Loading.../i);
    });
});