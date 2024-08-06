package eg.pharma.api.features.tablesmetadata;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TablesMetaDataService {

    private final TablesMetaDataRepository tablesMetaDataRepository;

    public TablesMetaDataService(TablesMetaDataRepository tablesMetaDataRepository) {
        this.tablesMetaDataRepository = tablesMetaDataRepository;
    }

    public List<TablesMetaData> getTablesMetaData(String tableName) {
        return tablesMetaDataRepository.findAllByTableName(tableName);
    }
}
