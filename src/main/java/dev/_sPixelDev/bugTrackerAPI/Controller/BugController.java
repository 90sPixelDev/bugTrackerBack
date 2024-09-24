package dev._sPixelDev.bugTrackerAPI.Controller;

import dev._sPixelDev.bugTrackerAPI.Entity.Bugs;
import dev._sPixelDev.bugTrackerAPI.Entity.Developers;
import dev._sPixelDev.bugTrackerAPI.Entity.HttpResponseHandler;
import dev._sPixelDev.bugTrackerAPI.Entity.Project;
import dev._sPixelDev.bugTrackerAPI.Repository.BugRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@CrossOrigin(value = "http://localhost:5173")
@RestController
public class BugController {
    private BugRepo bugRepo;

    @Autowired
    public void setBugRepo(BugRepo bugRepo) { this.bugRepo = bugRepo;}

    @GetMapping("/get/bugs")
    public ResponseEntity<Object> getBugs (@RequestParam(defaultValue = "0") int pageNumber) {

        try {
            Page<Bugs> bugs = bugRepo.findAll(PageRequest.of(pageNumber, 20, Sort.Direction.ASC, "bugId"));
            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Data successfully received", bugs.getContent());
        }
        catch (HttpClientErrorException e) {
            return HttpResponseHandler.generateResponse(e.getStatusCode(), true, e.getLocalizedMessage(), pageNumber);
        }
    };

    @GetMapping("/get/allbugs")
    public ResponseEntity<Object> getAllBugs (@RequestParam(defaultValue = "0") int pageNumber) {

        try {

            Pageable pageOptions = PageRequest.of(pageNumber, 20);
            Page<Bugs> bugs = bugRepo.getAllBugsListQry(pageOptions);
            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Data successfully received", bugs.getContent());
        }
        catch (HttpClientErrorException e) {
            return HttpResponseHandler.generateResponse(e.getStatusCode(), true, e.getLocalizedMessage(), pageNumber);
        }
    };

    @PostMapping("/add/bug")
    public ResponseEntity<Object> createNewDev (@RequestBody Bugs newBug) {
        try {
            newBug.setTimeCreated(LocalDateTime.now());
            bugRepo.save(newBug);
            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Successfully added new bug info to database", newBug);
        }
        catch (HttpClientErrorException e) {
            return HttpResponseHandler.generateResponse(e.getStatusCode(), true, e.getLocalizedMessage(), newBug);
        }
    };

    @PutMapping("update/bug/{bugId}")
    public ResponseEntity<Object> updateProject(@PathVariable String bugId, @RequestBody Bugs bugData) {

        try {
            Bugs bug = bugRepo.findById(Integer.valueOf(bugId)).get();
            if (!bugData.getBugName().equals(bug.getBugName())) {
                bug.setBugName(bugData.getBugName());
                bugRepo.save(bug);
            }
            if (!bugData.getBugDescription().equals(bug.getBugDescription())) {
                bug.setBugDescription(bugData.getBugDescription());
                bugRepo.save(bug);
            }
            if (!bugData.getPriority().equals(bug.getPriority())) {
                bug.setPriority(bugData.getPriority());
                bugRepo.save(bug);
            }
            if (bugData.getProjects() != bug.getProjects()) {
                bug.setProjects(bugData.getProjects());
                bugRepo.save(bug);
            }
            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Successfully updated project info in database", bugData);
        }
        catch (HttpClientErrorException e){
            return HttpResponseHandler.generateResponse(e.getStatusCode(), true, e.getLocalizedMessage(), bugData);
        }
    };

    @DeleteMapping("/delete/bug/{bugId}")
    public ResponseEntity<Object> createNewDev (@PathVariable int bugId) {
        try {
            bugRepo.deleteById(bugId);
            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Successfully deleted bug from database", null);
        }
        catch (HttpClientErrorException e) {
            return HttpResponseHandler.generateResponse(e.getStatusCode(), true, e.getLocalizedMessage(), null);
        }
    };
}
