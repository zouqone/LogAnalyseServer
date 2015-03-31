


function initGrid(url,condition){
  var grid = null;
  var columns = [
    {id: "id", name: "组件ID", field: "id"},
    {id: "comcategoryid", name: "组件分类ID", field: "comcategoryid"},
    {id: "code", name: "编码", field: "code"},
    {id: "name", name: "名称", field: "name"},
    {id: "detail", name: "描述", field: "detail"},
	{id: "createtime", name: "创建时间", field: "createtime"},
	{id: "creator", name: "创建人", field: "creator"},
	{id: "modifytime", name: "修改时间", field: "modifytime"},
	{id: "modifer", name: "修改人", field: "modifer"},
	{id: "ts", name: "时间戳", field: "ts"},
    {id: "dr", name: "删除标记", field: "dr"}
	
  ];

  var options = {
    enableCellNavigation: true,
    enableColumnReorder: false
  };
	var param = {condition : condition};
	if(condition == null){
		param = null;
	}
	var data = ajaxData(url,param);
    grid = new Slick.Grid("#myGrid", data, columns, options);
  
}

