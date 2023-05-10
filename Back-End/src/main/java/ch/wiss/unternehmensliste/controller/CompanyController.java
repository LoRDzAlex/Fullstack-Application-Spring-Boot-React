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
/*
    CompanyController zur Verwaltung von Firmendaten in einer Webanwendung
 */
@Controller
@RequestMapping("/company")
@CrossOrigin("*")
public class CompanyController {

    /**
     * Verbindet das CompanyRepository
     * @param companyRepository
     * @return verbundenes CompanyRepository
     */
    private CompanyRepository companyRepository;
    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository){this.companyRepository = companyRepository;}

    /**
     * Listet alle vorhandenen Firmen auf
     *
     * @return alle vorhandenen Firmen
     * @throws CompanyLoadException wenn etwas schief geht
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
     * Listet eine bestimmte Firma anhand der ID auf
     *
     * @param id
     *
     * @return spezifische Firma
     * @throws CompanyNotFoundException wenn etwas schief geht
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
     * Erstellt eine neue Firma
     *
     * @param companyName
     * @param website
     * @param canton
     * @return neue Firma
     * @throws CompanyCouldNotBeSavedException wenn etwas schief geht
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
     * Löscht eine Firma anhand der ID
     *
     * @param id
     *
     * @return gelöschte Firma
     * @throws CompanyCouldNotBeDeletedException wenn etwas schief geht
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
     * Updated eine Firma anhand der ID
     *
     * @param id
     * @param companyName
     * @param website
     * @param canton
     * @return updated Firma
     * @throws CompanyCouldNotBeUpdatedException wenn etwas schiefläuft
     */
    @PutMapping(path = "")
    public ResponseEntity<String> updateCompanyOld(@RequestParam int id,
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

    /**
     * Patched neue Firma
     * @param id
     * @param companyName
     * @param website
     * @param canton
     * @return patched Firma
     * @throws CompanyCouldNotBeUpdatedException wenn etwas schiefläuft
     */
    @PatchMapping(path = "")
    public ResponseEntity<String> updateCompany(@RequestParam int id,
                                                @RequestParam(required = false) String companyName,
                                                @RequestParam(required = false) String website,
                                                @RequestParam(required = false) String canton) {
        Company c = companyRepository.findById(id);
        if (c == null) {
            throw new CompanyCouldNotBeUpdatedException(id);
        }
        if (companyName != null && !companyName.equals(c.getCompanyName())) {
            c.setCompanyName(companyName);
        }
        if (website != null && !website.equals(c.getWebsite())) {
            c.setWebsite(website);
        }
        if (canton != null && !canton.equals(c.getCanton())) {
            c.setCanton(canton);
        }
        companyRepository.save(c);
        return ResponseEntity.ok("Updated " + c.getCompanyName());
    }

}
