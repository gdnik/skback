# skback

## Созданеи сертификата для бека

"Все файлы создавались на сервере"

"Руководстовался этой статьей https://srimalfernando.medium.com/ssl-for-java-spark-framework-on-jetty-deployed-in-a-docker-container-eda93657d13"

### Создание ключа jks

`keytool -genkeypair -alias simple-cert -keyalg RSA -keysize 2048 -keystore name_file.jks -dname "CN=getflow.me" -storepass password_for_file`

### Создание CSR по ключу

`keytool -certreq -alias simple-cert -keystore name_file.jks -file domain_name.com.csr -storepass password_for_file -ext san=dns:domain_name.com`

### Подключение certbot 

`sudo certbot certonly --manual  --csr getflow.me.csr --preferred-challenges "dns"` Далее следуем инструкциям, нажимаем enter только после того как txt в DNS запись добавится, проверить это можно так: \
`nslookup -type=TXT _acme-challenge.domain_name.com`

Далее мы получаем три сертификата и нам нужно переиминовать один из них, а именно расшширение `0001_chain.pem` в `simple-cert-with-chain.cer`\
### Подключение сертификата в хранилище ключей java
`keytool -importcert -alias simple-cert -keystore keystore.jks -storepass skillsy12345 -file simple-cert-with-chain.cer`\
Потом копируем этот файл по пути где лежит приложенеи бекенда в папку deploy \

### Минимальные изменения после получения сертификата

В самом бекенде нужно переписать пути, где запросы начинались как http и поменять на https, ведь теперь сертификат есть.






