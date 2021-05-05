package be.vdab.fietsen2.services;

import be.vdab.fietsen2.exceptions.DocentNietGevondenException;
import be.vdab.fietsen2.repositories.DocentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class DefaultDocentService implements DocentService{
    private final DocentRepository docentRepository;

    public DefaultDocentService(DocentRepository docentRepository) {
        this.docentRepository = docentRepository;
    }

    @Override
    public void opslag(long id, BigDecimal percentage) {
        docentRepository.findById(id)
                .orElseThrow(DocentNietGevondenException::new)
                .opslag(percentage);
    }
}
