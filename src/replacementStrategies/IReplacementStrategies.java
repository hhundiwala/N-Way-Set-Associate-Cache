package replacementStrategies;

import cache.DataBlock;
import cache.SetList;

public interface IReplacementStrategies {
    void replace(SetList list, DataBlock block);
}
