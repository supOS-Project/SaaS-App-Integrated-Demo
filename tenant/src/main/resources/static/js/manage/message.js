$ = layui.jquery;
layui.use('table', function () {
    let table = layui.table;
    table.render({
        elem: '#message'
        , url: '/message/list'
        , page: true
        , cellMinWidth: 80
        , cols: [[
            {field: 'type', title: '消息类型', sort: true, width: 200}
            , {field: 'action', title: '动作', width: 100}
            , {field: 'context', title: '消息正文'}
        ]]
    });
});

function subscribe() {
    $.ajax({
        url: "/message/subscribe", success: function (result) {
        }
    });
}

function readiness() {
    $.ajax({
        url: "/message/readiness", success: function (result) {
        }
    });
}