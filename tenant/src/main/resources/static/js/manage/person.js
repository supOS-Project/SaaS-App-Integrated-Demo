layui.use('table', function(){
    let table = layui.table;
    table.render({
        elem: '#person'
        ,url:'/person/list'
        ,page: true
        ,cellMinWidth: 80
        ,cols: [[
            {field:'code',  title: '人员编码', sort: true}
            ,{field:'name',  title: '人员名称'}
            ,{field:'modifyTime',  title: '更新时间', minWidth: 100}
        ]]
    });
});