package br.com.codart.controller;

import java.util.List;
import java.util.Arrays;
import br.com.codart.utils.Util;
import br.com.codart.model.Average;
import br.com.codart.model.Address;
import br.com.codart.model.AverageDTO;
import io.swagger.annotations.ApiOperation;
import br.com.codart.exception.BusinessException;
import br.com.codart.exception.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/mocks")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MockController {

    @GetMapping("/address/{cnpj}")
    @ApiOperation(value = "returns an address based on cnpj")
    public Address getAddress(@PathVariable String cnpj) {

        String document = Util.addMask(cnpj);
        if (!Util.isCnpj(document)) {
            throw new BusinessException("CNPJ invalid");
        }
        List<Address> address = Arrays
                .asList(new Address("58.443.544/0001-08", "Brasil", "São Paulo", "Campinas", "Rua 01", "01"),
                        new Address("70.385.366/0001-01", "Brasil", "Minas Gerais", "Arinos", "Rua Natal ", "369"),
                        new Address("31.181.593/0001-63", "Brasil", "Goiás", "Lago Azul", "Rua 03", "10"));

        return address.stream()
                .filter(ad -> ad.getCnpj().equals(document))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Address not found."));
    }

    @PostMapping("/averages")
    @ApiOperation(value = "returns the mean between two parameters")
    public AverageDTO getAverage(@RequestBody Average avg) {
        if (avg.getValueA() == null || avg.getValueB() == null) {
            throw new BusinessException("Fields are required.");
        }
        return new AverageDTO(avg.getValueA(), avg.getValueB());
    }

}
