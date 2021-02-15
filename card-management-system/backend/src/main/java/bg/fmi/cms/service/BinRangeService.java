package bg.fmi.cms.service;

import bg.fmi.cms.model.BinRange;

public interface BinRangeService {
    void addBinRange(BinRange binRange);
    void removeBinRange(BinRange binRange);
    void updateBinRange(BinRange binRange);
}
