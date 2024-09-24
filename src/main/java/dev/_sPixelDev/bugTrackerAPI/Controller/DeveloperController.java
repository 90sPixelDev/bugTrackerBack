package dev._sPixelDev.bugTrackerAPI.Controller;

import dev._sPixelDev.bugTrackerAPI.Entity.Developers;
import dev._sPixelDev.bugTrackerAPI.Entity.HttpResponseHandler;
import dev._sPixelDev.bugTrackerAPI.Entity.Project;
import dev._sPixelDev.bugTrackerAPI.Repository.DeveloperRepo;
import dev._sPixelDev.bugTrackerAPI.Repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.yaml.snakeyaml.introspector.Property;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class DeveloperController {
    private DeveloperRepo developerRepo;

    @Autowired
    public void setDeveloperRepo(DeveloperRepo developerRepo) { this.developerRepo = developerRepo;}

    private ProjectRepo projectRepo;

    @Autowired
    public void setProjectRepo(ProjectRepo projectRepo) {this.projectRepo = projectRepo; }

    @GetMapping("/get/devs")
    public ResponseEntity<Object> getDevelopers (@RequestParam(defaultValue = "0") int pageNumber) {

        try {
            Page<Developers> devs = developerRepo.findAll(PageRequest.of(pageNumber, 20, Sort.Direction.ASC, "devId"));
            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Data successfully received", devs.getContent());
        }
        catch (HttpClientErrorException e) {
            return HttpResponseHandler.generateResponse(e.getStatusCode(), true, e.getLocalizedMessage(), pageNumber);
        }
    };

    @GetMapping("/get/alldevs")
    public ResponseEntity<Object> getDevelopersOptimized (@RequestParam(defaultValue = "0") int pageNumber) {

        try {
            Pageable pageOptions = PageRequest.of(pageNumber, 20);
            Page<Developers> devs = developerRepo.getAllDevsOptimized(pageOptions);
            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Data successfully received", devs.getContent());
        }
        catch (HttpClientErrorException e) {
            return HttpResponseHandler.generateResponse(e.getStatusCode(), true, e.getLocalizedMessage(), pageNumber);
        }
    };

    @GetMapping("/get/dev")
    public ResponseEntity<Object> getDeveloperById (@RequestParam int devId) {

        try {
            Developers dev = developerRepo.getDevById(devId);
            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Data successfully received", dev);
        }
        catch (HttpClientErrorException e) {
            return HttpResponseHandler.generateResponse(e.getStatusCode(), true, e.getLocalizedMessage(), devId);
        }
    };

    @PostMapping("/add/dev")
    public ResponseEntity<Object> createNewDev (@RequestBody Developers dev) {
        try {
            developerRepo.save(dev);
            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Successfully added new Developer info to database", dev);
        }
        catch (HttpClientErrorException e) {
            return HttpResponseHandler.generateResponse(e.getStatusCode(), true, e.getLocalizedMessage(), dev);
        }
    };

    @PutMapping("update/dev/{devId}/{projectId}")
    public ResponseEntity<Object> addDevProject(@PathVariable String devId, @PathVariable String projectId) {

        try {
            Project project = projectRepo.findById(Integer.valueOf(projectId)).get();
            Developers dev = developerRepo.findById(Integer.valueOf(devId)).get();

            dev.getProjects().add(project);
            developerRepo.save(dev);

            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Successfully updated project info in database", dev);
        }
        catch (HttpClientErrorException e){
            return HttpResponseHandler.generateResponse(e.getStatusCode(), true, e.getLocalizedMessage(), null);
        }
    };

    @DeleteMapping("/delete/dev/{devId}")
    public ResponseEntity<Object> createNewDev (@PathVariable int devId) {
        try {
            developerRepo.deleteById(devId);
            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Successfully deleted developer from database", null);
        }
        catch (HttpClientErrorException e) {
            return HttpResponseHandler.generateResponse(e.getStatusCode(), true, e.getLocalizedMessage(), null);
        }
    };
}
