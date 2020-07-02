var user = {
    login: function () {
        var cookie_date = new Date();
        var cookieName = "userName";
        var curr_name = null;
        var matches = document.cookie.match(new RegExp(
            "(?:^|; )" + cookieName.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
        ));

        if (matches != null) {
            cookie_date.setTime(cookie_date.getTime() -1); //log off
            document.cookie = "userName = ; expires= " + cookie_date.toGMTString();
            document.cookie = "userId = ; expires= " + cookie_date.toGMTString();
            document.location.href = 'index.html';
        }else {
            var login = document.getElementById("login").value;
            var pass = document.getElementById("password").value;
            $.ajax({
                type: "POST",
                url: "/TractorParts",
                dataType: "json",
                data: {requestType: "login", login: login, password: pass},
                success: function (data) {
                    if (data.name == "error") {
                        if (data.result == "login") {
                            alert("Такого пользователя не существует!");
                        } else if (data.result == "pass") {
                            alert("Не правильный пароль!");
                        } else {
                            alert("Ошибка входа на сайт!");
                        }
                    } else {
                        //alert(data.name);
                        cookie_date.setTime(cookie_date.getTime() + 3600 * 1000); //during 1 hr
                        document.cookie = "userName = " + data.name + "; expires=" + cookie_date.toGMTString();
                        document.cookie = "userId = " + data.result + "; expires=" + cookie_date.toGMTString();
                        document.location.href = 'index.html';
                    }
                }
            });
        }
    },

    registration: function () {
        var login = document.getElementById("login_reg").value;
        var pass1 = document.getElementById("pass1_reg").value;
        var pass2 = document.getElementById("pass2_reg").value;
        var name  = document.getElementById("name_reg").value;
        var phone = document.getElementById("phone_reg").value;

        var cookieName = "userId";
        var curr_id = null;
        var matches = document.cookie.match(new RegExp(
            "(?:^|; )" + cookieName.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
        ));
        if (matches != null) {
            curr_id = matches[1];
        }

        $.ajax({
            type: "POST",
            url: "/TractorParts",
            dataType: "json",
            data: {
                requestType: "registration",
                flag: "registration",
                user_id:    curr_id,
                login_reg:  login,
                pass1_reg:  pass1,
                pass2_reg:  pass2,
                name_reg:   name,
                phone_reg:  phone
            },
            success: function (data) {
                if (data.name == "error") {
                    if (data.result == "passErr")
                        alert("Пароль и подтверждение пароля должны совпадать");
                    else if (data.result == "loginErr")
                        alert("В качестве логина должна быть Ваша почта");
                    else if (data.result == "userExist")
                        alert("Такой пользователь уже существует. Регистрация не возможна.");
                    else if (data.result == "nameErr")
                        alert("Укажите свое ФИО(Наименование организации)");
                    else if (data.result == "phoneErr")
                        alert("Укажите свой контактный телефон");
                    else if (data.result == "notFound")
                        alert("Пользователь не найден. Ошибка обновления.");
                    else if (data.result == "paramLength")
                        alert("Превышена допустимое количество символов\n    Логин (64)\n    Пароль (32)\n    ФИО (Организация)(128)\n    Телефон (128)\n");
                    else
                        alert("Ошибка регистрации");
                } else if (data.name == "create") {
                    alert("Пользователь " + data.result + " успешно зарегистрирован");
                    document.location.href = 'index.html';
                } else if (data.name == "update") {
                    alert("Информация успешно обновлена");
                    document.getElementById("userName").innerHTML = data.result;
                    var cookie_date = new Date();
                    cookie_date.setTime(cookie_date.getTime() + 3600 * 1000); //during 1 hr
                    document.cookie = "userName = " + data.result + "; expires=" + cookie_date.toGMTString();
                }
            }
        });
    },


    getRegistrationData: function () {
        var name = "userId";
        var matches = document.cookie.match(new RegExp(
            "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
        ));
        if (matches != null) {
            var id = matches[1];
            $.ajax({
                type: "POST",
                url: "/TractorParts",
                dataType: "json",
                data: {requestType: "registration", flag: "getRegistrationData", user_id: id},
                success: function (data) {
                    if (data.name == "data") {
                        document.getElementById("login_reg").value = data.user_login;
                        document.getElementById("pass1_reg").value = data.user_pass;
                        document.getElementById("pass2_reg").value = data.user_pass;
                        document.getElementById("name_reg").value =  data.user_name;
                        document.getElementById("phone_reg").value = data.user_phone;
                    }
                }
            });

        }
    },

    currentUserName: function () {
        var name = "userName";
        var matches = document.cookie.match(new RegExp(
            "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
        ));
        if (matches != null) {
            document.getElementById("userName").innerHTML = decodeURIComponent(matches[1]);
            if (document.getElementById("save_button") != null) {
                document.getElementById("save_button").innerHTML = 'Сохранить';
            }
            if (document.getElementById("reg_button") != null) {
                document.getElementById("reg_button").innerHTML = "Кабинет";
            }
            if (document.getElementById("login_button") != null) {
                document.getElementById("login_button").innerHTML = "Выйти";
            }
            if (document.getElementById("operationType") != null) {
                document.getElementById("operationType").innerHTML = "Измение ваших данных";
            }
        } else {
            document.getElementById("userName").innerHTML = "Вы зашли как розничный пользователь";
            if (document.getElementById("save_button") != null) {
                document.getElementById("save_button").innerHTML = 'Регистрация';
            }
            if (document.getElementById("reg_button") != null) {
                document.getElementById("reg_button").innerHTML = "Регистрация";
            }
            if (document.getElementById("login_button") != null) {
                document.getElementById("login_button").innerHTML = "Войти";
            }
            if (document.getElementById("operationType") != null) {
                document.getElementById("operationType").innerHTML = "Регистрация нового пользователя";
            }
        }
    },

};