package bg.fmi.cms.service;

import bg.fmi.cms.model.Bin;
import bg.fmi.cms.model.BinRange;

import java.util.List;

public interface BinRangeService {
    void addBinRange(BinRange binRange);
    void removeBinRange(BinRange binRange);
    void updateBinRange(BinRange binRange);
    List<BinRange> getAll();
    List<Bin> getBinsInRange(Long rangeId);
    void addBinInRange(Long binRangeId, Bin bin);
    BinRange getRangeById(Long id);

}
