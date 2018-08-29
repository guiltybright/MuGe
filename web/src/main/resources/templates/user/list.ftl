<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
</head>
<body>
    <table>
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>地址</td>
            <td>操作</td>
        </tr>
        <#list users.content as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.age!}</td>
                <td>${user.address!}</td>
                <td>
                    <a href="#">查看</a>
                    <a href="#">修改</a>
                    <a href="${ctx}/user/delete/${user.id}">删除</a>
                </td>
            </tr>
        </#list>
    </table>
</body>
</html>