package com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("prices")
public class PriceController
{
    @GetMapping(value="/{id}", produces = "application/json")
    public @ResponseBody CommissionedPrice getPrice(@PathVariable Long id)
    {
        return findLatestPriceById(id);
    }

    private CommissionedPrice findLatestPriceById(Long id) {
        return null; //todo read from storage
    }
}
