package st.projects.assignments.billogram.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import st.projects.assignments.billogram.repository.model.DiscountCode;
import st.projects.assignments.billogram.service.discount.generator.OutOfStockException;
import st.projects.assignments.billogram.service.discount.DiscountService;

import java.util.List;

@RequestMapping("/discounts")
@RestController
public class DiscountCodeController {

    final DiscountService discountService;
    final Logger LOGGER = LoggerFactory.getLogger(DiscountCodeController.class);

    public DiscountCodeController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/generate")
    public ResponseEntity<String> getCode(@RequestParam long campaignId, @RequestParam long userId) {
        try {
            String code = discountService.generateCode(campaignId, userId);
            return new ResponseEntity<>(code, HttpStatus.OK);
        } catch (OutOfStockException e) {
            LOGGER.warn("Error: " + e.getMessage(), e);
            return new ResponseEntity<>("no discount code available", HttpStatus.OK);
        }
    }

    @GetMapping()
    public ResponseEntity<List<DiscountCode>> listCodes(@RequestParam long campaignId) {
        final List<DiscountCode> discountCodes = discountService.findByCampaignId(campaignId);
        return new ResponseEntity<>(discountCodes, HttpStatus.OK);

    }
}
