package st.projects.assignments.billogram.web.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import st.projects.assignments.billogram.repository.BrandRepository;
import st.projects.assignments.billogram.repository.CampaignRepository;
import st.projects.assignments.billogram.repository.model.Campaign;
import st.projects.assignments.billogram.service.campaign.CampaignService;

@RequestMapping("/campaigns")
@RestController
public class CampaignController {

    final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    public ResponseEntity<Long> addCampaign(@RequestBody Campaign campaign) {
        campaignService.save(campaign);
        return new ResponseEntity<>(campaign.id, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Campaign>> list() {
        final Iterable<Campaign> list = campaignService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
