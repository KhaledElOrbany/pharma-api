package eg.pharma.api.features.tablesmetadata;

import jakarta.persistence.*;

@Entity
@Table(name = "tables_meta_data")
public class TablesMetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tableName;
    private String columnName;
    private String columnType;
    private Integer columnOrder;
    private Boolean hasLink;
    private String baseLink;
    private String linkTo;
    private Boolean isSearchable;
    private Boolean isSortable;
    private Boolean isVisible;

    public TablesMetaData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public Integer getColumnOrder() {
        return columnOrder;
    }

    public void setColumnOrder(Integer columnOrder) {
        this.columnOrder = columnOrder;
    }

    public Boolean getHasLink() {
        return hasLink;
    }

    public void setHasLink(Boolean hasLink) {
        this.hasLink = hasLink;
    }

    public String getBaseLink() {
        return baseLink;
    }

    public void setBaseLink(String baseLink) {
        this.baseLink = baseLink;
    }

    public String getLinkTo() {
        return linkTo;
    }

    public void setLinkTo(String linkTo) {
        this.linkTo = linkTo;
    }

    public Boolean getSearchable() {
        return isSearchable;
    }

    public void setSearchable(Boolean searchable) {
        isSearchable = searchable;
    }

    public Boolean getSortable() {
        return isSortable;
    }

    public void setSortable(Boolean sortable) {
        isSortable = sortable;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }
}
