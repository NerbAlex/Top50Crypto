# Top50Crypto
Стек : Kotlin, MVVM, RXJava3, Dagger2, Room, Retrofit, OkhhtpInterceptor, Glide, viewBinding

![one](https://user-images.githubusercontent.com/87127659/128188132-66f6e301-a21e-4cc6-b109-e9a32516e989.png)
![two](https://user-images.githubusercontent.com/87127659/128188173-b0db9891-992f-4ae8-a120-78788e7c5cfc.png)


тестами не покрыто.

# ТЗ:
Написать мини приложение для просмотра курса криптовалют. Приложение должно
отображать список самых популярных криптовалют (минимальное количество - 20).
Также должна быть возможность поиска по названию криптовалюты. В случае если для
введенного поискового запроса соответствует несколько вариантов, то отобразить их все.
Приложение должно кэшировать последние значения курса и отображать их при
следующем входе.

Технические требования
* kotlin
* Покрыть тестами
* Сохранение последнего успешного результата в БД (не имеет значения, SQL, Realm или
sharedPreference)
* Добавить обработку ошибок в случае если нет интернета или любой другой сетевой
ошибки и отобразить это на UI в виде алерта с просьбой присоединиться к интернету.
