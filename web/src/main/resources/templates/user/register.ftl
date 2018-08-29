<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <script type="application/javascript" src="/static/js/jQuery.1.8.2.min.js"></script>
    <script type="text/javascript">
        function checkUserName(userName) {
            var value = userName.value;
            console.log(123213);
            console.log(${ctx});
            if (value != "" && value != null) {
                $.ajax({
                    url: ${ctx}+"/user/checkUserName?userName=" + value,
                    type: "GET",
                    success: function (data) {
                        console.log(data);
                    }
                })
            } else {
                alert("用户名不能为空");
            }
        }
    </script>
</head>
<body>
    <form>
        <table>
            <tr>
                <td>用户名</td>
                <td>
                    <input type="text" name="userName" onblur="checkUserName(this)">
                </td>
            </tr>
            <tr>
                <td>密码</td>
                <td>
                    <input type="password" name="password">
                </td>
            </tr>
            <tr>
                <td>年龄</td>
                <td>
                    <input type="text" name="age">
                </td>
            </tr>
            <tr>
                <td>地址</td>
                <td>
                    <input type="text" name="address">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="注册">
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>