package ch.wiss.unternehmensliste.controller;

import ch.wiss.unternehmensliste.exception.couldnotbedeleted.CompanyCouldNotBeDeletedException;
import ch.wiss.unternehmensliste.exception.couldnotbesaved.CompanyCouldNotBeSavedException;
import ch.wiss.unternehmensliste.exception.couldnotbeupdated.CompanyCouldNotBeUpdatedException;
import ch.wiss.unternehmensliste.exception.load.CompanyLoadException;
import ch.wiss.unternehmensliste.exception.notfound.CompanyNotFoundException;
import ch.wiss.unternehmensliste.model.Company;
import ch.wiss.unternehmensliste.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/company")
@CrossOrigin("*")
public class CompanyController {

    /**
     * Wire CompanyRepository
     * @param companyRepository
     * @return wired CompanyRepository
     */
    private CompanyRepository companyRepository;
    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository){this.companyRepository = companyRepository;}

    /**
     * List all existing Companies
     *
     * @return all existing Companies
     * @throws CompanyLoadException if something went wrong
     */
    @GetMapping(path = "")
    public ResponseEntity<Iterable<Company>> getAllCompanies() {
        Iterable<Company> companies = null;

        try{
            companies = companyRepository.findAll();
        }catch (Exception ex){
            throw new CompanyLoadException();
        }

        return ResponseEntity.ok(companies);
    }

    /**
     * List specific Company by ID
     *
     * @param id
     *
     * @return specific Company
     * @throws CompanyNotFoundException if something went wrong
     */
    @GetMapping(path = "/id")
    public ResponseEntity<Company> getCompanyById(@RequestParam int id){
        Company company = null;

        try{
            company = companyRepository.findById(id);
        }catch (Exception ex){
            throw new CompanyNotFoundException(id);
        }
        return ResponseEntity.ok(company);
    }

    /**
     * Creates new Company
     *
     * @param companyName
     * @param website
     * @param canton
     * @return new Company
     * @throws CompanyCouldNotBeSavedException if something went wrong
     */
    @PostMapping(path = "")
    public ResponseEntity<String> createCompany(
            @RequestParam String companyName,
            @RequestParam String website,
            @RequestParam String canton
    ){
        try {
            Company company = new Company(companyName, website, canton);
            companyRepository.save(company);
        }catch (Exception ex){
            throw new CompanyCouldNotBeSavedException(companyName);
        }
        return ResponseEntity.ok("Saved " + companyName);
    }

    /**
     * Deletes Company by ID
     *
     * @param id
     *
     * @return deleted Company
     * @throws CompanyCouldNotBeDeletedException if something went wrong
     */
    @DeleteMapping(path = "")
    public ResponseEntity<String> deleteCompany(@RequestParam int id){
        try{
            companyRepository.deleteById(id);
        }catch (Exception ex){
            throw new CompanyCouldNotBeDeletedException(id);
        }
        return ResponseEntity.ok("Deleted");
    }

    /**
     * Updates Company by ID
     *
     * @param id
     * @param companyName
     * @param website
     * @param canton
     * @return updated Company
     * @throws CompanyCouldNotBeUpdatedException if something went wrong
     */
    @PutMapping(path = "")
    public ResponseEntity<String> updateCompany(@RequestParam int id,
                                                @RequestParam String companyName,
                                                @RequestParam String website,
                                                @RequestParam String canton){
        Company c = companyRepository.findById(id);
        try{
            c.setCompanyName(companyName);
            c.setWebsite(website);
            c.setCanton(canton);
            companyRepository.save(c);
        }catch (Exception ex){
            throw new CompanyCouldNotBeUpdatedException(id);
        }
        return ResponseEntity.ok("Updated "+ companyName);
    }

}
