# Top50Crypto
Стек : Kotlin, MVVM, RXJava3, Dagger2, Room, Retrofit, OkhhtpInterceptor
тестами не покрыто.

#ТЗ 
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
