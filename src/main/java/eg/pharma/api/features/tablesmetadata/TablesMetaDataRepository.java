package eg.pharma.api.features.tablesmetadata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TablesMetaDataRepository extends JpaRepository<TablesMetaData, Long> {
    List<TablesMetaData> findAllByTableName(String tableName);
}
