package eg.pharma.api.base;

import eg.pharma.api.features.tablesmetadata.TablesMetaData;
import eg.pharma.api.features.tablesmetadata.TablesMetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

public class BaseController {

    protected int page = 0;
    protected int size = 5;
    protected Map<String, String[]> params;

    private TablesMetaDataService tablesMetaDataService;

    @Autowired
    public void setTablesMetaDataService(TablesMetaDataService tablesMetaDataService) {
        this.tablesMetaDataService = tablesMetaDataService;
    }

    public List<HashMap<String, ?>> getTablesMetaData(String tableName) {
        List<TablesMetaData> tablesMetaData = tablesMetaDataService.getTablesMetaData(tableName);
        List<HashMap<String, ?>> metaData = new ArrayList<>();

        for (TablesMetaData tableMetaData : tablesMetaData) {
            metaData.add(new HashMap<>(){{
                put("tableName", tableMetaData.getTableName());
                put("columnName", tableMetaData.getColumnName());
                put("columnType", tableMetaData.getColumnType());
                put("hasLink", tableMetaData.getHasLink());
                put("link", tableMetaData.getLink());
                put("isSearchable", tableMetaData.getSearchable());
                put("isSortable", tableMetaData.getSortable());
                put("isVisible", tableMetaData.getVisible());
            }});
        }

        return metaData;
    }

    @ModelAttribute
    protected void setWebRequest(WebRequest webRequest) {
        this.params = webRequest.getParameterMap();
        preparePagination();
    }

    protected ApiResponse respond() {
        return ApiResponse.respond(HttpStatus.OK);
    }

    protected <T> ApiResponse respond(T data) {
        return ApiResponse.respond(data, HttpStatus.OK);
    }

    protected <T, M> ApiResponse respond(T data, M meta) {
        return ApiResponse.respond(data, meta, HttpStatus.OK);
    }

    protected void preparePagination() {
        try {
            this.page = Integer.parseInt(params.get("page")[0]);
            this.size = Integer.parseInt(params.get("size")[0]);
        } catch (Exception e) {
            this.page = 0;
            this.size = 5;
        }
    }
}
