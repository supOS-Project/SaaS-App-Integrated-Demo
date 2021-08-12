layui.use('table', function(){
    let table = layui.table;
    table.render({
        elem: '#department'
        ,url:'/department/list'
        ,page: true
        ,cellMinWidth: 80
        ,cols: [[
            {field:'code',  title: '部门编码', sort: true}
            ,{field:'name',  title: '部门名称'}
            ,{field:'parentCode',  title: '上级部门编码'}
            ,{field:'fullPath',  title: '部门全路径', minWidth: 100}
            ,{field:'description',  title: '部门描述', sort: true}
        ]]
    });
});