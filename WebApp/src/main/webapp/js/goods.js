var goods = {

    fillDB: function () {
        $.ajax({
            type: "POST",
            url: "/TractorParts",
            dataType: "json",
            data: {requestType: "goods", name: "fillDB"},
            success: function (data) {
                if (data.result == "notActiv") {
                    alert("Эта функция сейчас недоступна");
                } else if (data.result == "error") {
                    alert("Ошибка");
                }
            }
        });
    },

    groupList: function () {
        //alert("group");
        $.ajax({
            type: "POST",
            url: "/TractorParts",
            dataType: "json",
            data: {requestType: "group"},
            success: function (data) {
                if (data.result == "SQL_error") {
                    alert("Ошибка при попытки загрузить каталоги продукции.")
                } else {
                    var arr = data.result.split(";");
                    var htmlText = "<p><small>";
                    for (i = 0; i < arr.length / 3; i++) {
                        if (arr[i * 3] == 2) {
                            htmlText = htmlText + "<t></t>"
                        }
                        htmlText = htmlText + "<a onClick=\u0022goods.findGoodsByGruopId(" + arr[i * 3 + 1] + ")\u0022>" +
                            arr[i * 3 + 2] + "</a><br>";
                    }
                    htmlText = htmlText + "</small></p>"
                    document.getElementById("group_html").innerHTML = htmlText;
                }
            }
        });
    },

    findGoods: function () {
        var findStr = document.getElementById("search_str").value;
        $.ajax({
            type: "POST",
            url: "/TractorParts",
            dataType: "json",
            data: {requestType: "goods", name: "findByStr", findStr: findStr},
            success: function (data) {
                if (data.result == "SQL_error") {
                    alert("Ошибка.")
                } else {
                    var arr = data.result.split(";");
                    //alert(arr.length);
                    var htmlText = "<table border='1'>" +
                        "<tr>" +
                        "<th class='first'>Код</th>" +
                        "<th class='first'>Артикул</th>" +
                        "<th class='first'>Наименование</th>" +
                        "<th class='first'>Страна</th>" +
                        "<th class='first'>Цена</th>" +
                        "<th class='first'></th>" +
                        "</tr>";

                    for (i = 0; i < arr.length / 9; i++) {
                        htmlText = htmlText +
                            "<tr>" +
                            "<td> " + arr[i * 9] + "</td>" + //id
                            "<td> " + arr[i * 9 + 1] + "</td>" + //vendor
                            "<td> " + arr[i * 9 + 2] + "</td>" + //name
                            "<td> " + arr[i * 9 + 6] + "</td>" + //country
                            "<td class='price'> " + arr[i * 9 + 4] + "</td>" + //price
                            "<td> <img src='images/koshik.png' width='15' height='15'"+
                            "onclick='goods.addToBasket(" + arr[i * 9] + ")'></td>" +
                            "</tr>";
                    }
                    htmlText = htmlText + "</table>";

                    document.getElementById("goods_html").innerHTML = htmlText;
                }
            }
        });
    },

    addToBasket: function (id) {
        alert("Тут будет добавление в корзину "+id);
    },


    findGoodsByGruopId: function (groupId) {
        //alert(groupId);
        $.ajax({
            type: "POST",
            url: "/TractorParts",
            dataType: "json",
            data: {requestType: "goods", name: "getGoodsByGroupId", groupId: groupId},
            success: function (data) {
                if (data.result == "SQL_error") {
                    alert("Ошибка при попытки загрузить каталоги продукции.")
                } else {
                    var arr = data.result.split(";");
                    //alert(arr.length);
                    var htmlText = "<table border='1'>" +
                        "<tr>" +
                        "<th class='first'>Код</th>" +
                        "<th class='first'>Артикул</th>" +
                        "<th class='first'>Наименование</th>" +
                        "<th class='first'>Страна</th>" +
                        "<th class='first'>Цена</th>" +
                        "<th class='first'></th>" +
                        "</tr>";

                    for (i = 0; i < arr.length / 9; i++) {
                        htmlText = htmlText +
                            "<tr>" +
                            "<td> " + arr[i * 9] + "</td>" + //id
                            "<td> " + arr[i * 9 + 1] + "</td>" + //vendor
                            "<td> " + arr[i * 9 + 2] + "</td>" + //name
                            "<td> " + arr[i * 9 + 6] + "</td>" + //country
                            "<td class='price'> " + arr[i * 9 + 4] + "</td>" + //price
                            "<td> <img src='images/koshik.png' width='15' height='15'"+
                                "onclick='goods.addToBasket(" + arr[i * 9] + ")'></td>" +
                            "</tr>";
                    }
                    htmlText = htmlText + "</table>";

                    document.getElementById("goods_html").innerHTML = htmlText;
                }
            }
        });
    }


};