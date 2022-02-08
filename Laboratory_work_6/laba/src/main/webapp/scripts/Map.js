ymaps.ready(init);
function init(){
    let login = document.getElementById('help').value;
    console.log("login");
    // Создаём объект класса XMLHttpRequest
    const request = new XMLHttpRequest();
    const url = "\\getgeo?user_login=" + login;
    request.open('GET', url);
    // Указываем заголовки для сервера, говорим что тип данных, - контент который мы хотим получить должен быть не закодирован.
    request.setRequestHeader('Content-Type', 'application/x-www-form-url');
    // Здесь мы получаем ответ от сервера на запрос, лучше сказать ждем ответ от сервера
    request.addEventListener("readystatechange", () => {
        // request.readyState - возвращает текущее состояние объекта XHR(XMLHttpRequest) объекта,
        // бывает 4 состояния 4-е состояние запроса - операция полностью завершена, пришел ответ от сервера,
        // вот то что нам нужно request.status это статус ответа,
        // нам нужен код 200 это нормальный ответ сервера, 401 файл не найден, 500 сервер дал ошибку и прочее...
        if (request.readyState === 4 && request.status === 200) {
            console.log("All good");
            // выводим в консоль то что ответил сервер
            let jsonCootdinates = JSON.parse(request.responseText);
            let latitude = jsonCootdinates['latitude'];
            let longitude = jsonCootdinates['longitude'];
            if( (latitude !== null) && (longitude !== null)) {
                // Сделать проверку на null координаты!
                console.log(latitude);
                console.log(longitude);
                // Создание карты.
                var myMap = new ymaps.Map("map", {
                    // Координаты центра карты. Порядок по умолчанию: «широта, долгота».
                    center: [latitude, longitude],
                    // Уровень масштабирования. Допустимые значения:
                    // от 0 (весь мир) до 19.
                    zoom: 11
                }), myPlacemark = new ymaps.Placemark(myMap.getCenter(), {
                    hintContent: 'Последнее место входа',
                    //balloonContent: 'Это красивая метка'
                }, {
                    // Опции.
                    // Необходимо указать данный тип макета.
                    iconLayout: 'default#image',
                    // Размеры метки.
                    iconImageSize: [30, 42],
                    // Смещение левого верхнего угла иконки относительно
                    // её "ножки" (точки привязки).
                    iconImageOffset: [-5, -38]
                });
                myMap.geoObjects.add(myPlacemark);
            }
            else{
                let mapDiv = document.getElementById('map');
                mapDiv.innerHTML = "<img src=\"/images/video.jpg\" alt=\"oops\"/>";
            }
        }
    });
    request.send(); // Выполняем запрос
}