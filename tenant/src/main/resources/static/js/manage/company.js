layui.use('table', function(){
    let table = layui.table;
    table.render({
        elem: '#company'
        ,url:'/company/list'
        ,page: true
        ,cellMinWidth: 80
        ,cols: [[
            {field:'code',  title: '公司编码', sort: true}
            ,{field:'shortName',  title: '公司简称'}
            ,{field:'fullName',  title: '公司全称', sort: true}
            ,{field:'parentCode',  title: '上级公司编码'}
            ,{field:'fullPath',  title: '公司全路径', minWidth: 100}
            ,{field:'description',  title: '公司描述', sort: true}
        ]]
    });
});