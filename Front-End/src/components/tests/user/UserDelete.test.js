import React from 'react';
import {fireEvent, render, screen, waitFor} from '@testing-library/react';
import UserDelete from "../../delete/UserDelete";
import {getCurrentUserToken} from "../../api/auth/auth.service";

describe('UserDelete', () => {
    it('calls handleDelete when delete button is clicked', async () => {
        global.fetch = jest.fn(() => Promise.resolve({ json: () => ({ success: true }) }));
        const userId = 1;
        render(<UserDelete userId={userId} />);

        const deleteButton = screen.getByRole('button');
        fireEvent.click(deleteButton);

        await waitFor(() =>
            expect(fetch).toHaveBeenCalledWith(`http://localhost:8080/user/${userId}`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + getCurrentUserToken(),
                },
                redirect: 'follow'
            })
        );
    });
});