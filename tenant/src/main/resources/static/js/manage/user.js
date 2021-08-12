layui.use('table', function(){
    let table = layui.table;
    table.render({
        elem: '#user'
        ,url:'/user/list'
        ,page: true
        ,cellMinWidth: 80
        ,cols: [[
            {field:'name',  title: '用户名称', sort: true}
            ,{field:'personCode',  title: '人员编码'}
            ,{field:'personName',  title: '人员名称', sort: true}
            ,{field:'createTime',  title: '创建时间'}
            ,{field:'modifyTime',  title: '更新时间'}
        ]]
    });
});