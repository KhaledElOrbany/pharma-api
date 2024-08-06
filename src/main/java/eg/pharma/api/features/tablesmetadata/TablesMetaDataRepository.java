package eg.pharma.api.features.tablesmetadata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TablesMetaDataRepository extends JpaRepository<TablesMetaData, Long> {

    @Query("SELECT t FROM TablesMetaData t WHERE t.tableName = :tableName ORDER BY t.columnOrder")
    List<TablesMetaData> findAllByTableName(@Param("tableName") String tableName);

}
