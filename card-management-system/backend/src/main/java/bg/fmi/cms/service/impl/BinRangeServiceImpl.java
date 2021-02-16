package bg.fmi.cms.service.impl;


import bg.fmi.cms.model.Bin;
import bg.fmi.cms.model.BinRange;
import bg.fmi.cms.repo.BinRangeRepository;
import bg.fmi.cms.repo.BinRepository;
import bg.fmi.cms.service.BinRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinRangeServiceImpl implements BinRangeService {

    @Autowired
    BinRangeRepository binRangeRepository;

    @Autowired
    BinRepository binRepository;

    @Override
    public void addBinRange(BinRange binRange) {
        binRangeRepository.save(binRange);
    }

    @Override
    public void removeBinRange(BinRange binRange) {
        binRangeRepository.delete(binRange);
    }

    @Override
    public void updateBinRange(BinRange binRange) {
        binRangeRepository.save(binRange);
    }

    @Override
    public List<BinRange> getAll() {
        return (List<BinRange>) binRangeRepository.findAll();
    }

    @Override
    public List<Bin> getBinsInRange(Long rangeId) {
        return (List<Bin>) binRangeRepository.findById(rangeId).get().getBins();
    }

    @Override
    public void addBinInRange(Long binRangeId, Bin bin) {
        bin.setBinRange(binRangeRepository.findById(binRangeId).get());
        binRepository.save(bin);
    }

    @Override
    public BinRange getRangeById(Long id) {
        return binRangeRepository.findById(id).get();
    }
}
