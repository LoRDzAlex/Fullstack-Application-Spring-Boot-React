import {render, screen} from "@testing-library/react";
import CompanyContactList from "../../list/CompanyContactList";

describe("CompanyContactList", () => {
    const props = {
        id: 1,
        companyName: "Test Company",
        website: "www.test.com",
        canton: "Bern",
        contactName: "John Doe",
        gender: "M",
        tel: "1234567890",
        email: "john.doe@test.com",
        contactId: 2,
    };

    it("should display company information", () => {
        render(<CompanyContactList {...props} />);

        const companyName = screen.getByText("Test Company");
        expect(companyName).toBeInTheDocument();

        const website = screen.getByText("www.test.com");
        expect(website).toBeInTheDocument();

        const canton = screen.getByText("Bern");
        expect(canton).toBeInTheDocument();
    });
});