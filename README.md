# Tasarım Desenleri Ve Optimize Edilmiş Yazılım Mimarisi(Otopark Yönetim Sistemi)  

## Proje Hakkında  
Bu proje, yazılım tasarım desenlerini kullanarak modüler, esnek ve yeniden kullanılabilir bir mimari oluşturmayı amaçlamaktadır. Yazılım geliştirme sürecinde yaygın olarak kullanılan **Singleton**, **Factory Method**, **Builder**, **State** ve **DTO** tasarım desenleri, proje kapsamındaki ana bileşenlerde etkili bir şekilde uygulanmıştır.  

## Kullanılan Tasarım Desenleri  

### 1. **Singleton Design Pattern**  
- **Amaç**: Bir sınıfın yalnızca bir örneğinin oluşturulmasını sağlamak ve bu örneği global bir erişim noktası üzerinden yönetmek.  
- **Projedeki Uygulama**:  
  - Spring Framework, @Service, @Component, @Repository gibi anotasyonlar ile işaretlenmiş sınıfları varsayılan olarak singleton olarak yönetir. Bu, sınıfın yalnızca bir örneğinin Spring IoC (Inversion of Control) konteyneri tarafından yaratılacağı anlamına gelir. Böylece, her bir sınıf için yalnızca bir nesne oluşturulur ve bu nesne, uygulama boyunca kullanılır. Ancak EmailService classında bu durum manuel olarak ele alınmıştır. Bu durumda, sınıfın örneğini daha sıkı bir şekilde kontrol etmek amacından meydana gelmiştir.

### 2. **Factory Method Design Pattern**  
- **Amaç**: Alt sınıfların hangi sınıf örneğini oluşturacağını belirlemesine olanak tanımak ve nesne oluşturma sürecini soyutlamak.  
- **Projedeki Uygulama**:  
  - Factory Design Pattern, bu projede farklı araç servislerine ait nesnelerin oluşturulması ve istemciye sağlanmasında kullanılmıştır. Bu desenin sağladığı merkezi nesne yönetimi, kodun bakımını ve genişletilmesini kolaylaştırır. İstemci kodunun hangi tür servis gerektiğini belirtmesi yeterli olur, servislerin nasıl oluşturulacağı ise AracServiceFactory tarafından yönetilir.  

### 3. **Builder Design Pattern**  
- **Amaç**: Karmaşık nesnelerin adım adım oluşturulmasını sağlamak ve oluşturma sürecini kontrol etmek.  
- **Projedeki Uygulama**:  
  - Bu projede Builder Design Pattern, Arac nesnesinin oluşturulmasında kullanılmıştır.  

### 4. **State Design Pattern**  
- **Amaç**: Nesnelerin durumlarına bağlı olarak davranışlarını değiştirebilmek.  
- **Projedeki Uygulama**:  
  - Bu projede faturaya uygulanması gereken indirim miktarının belirlenmesi amacıyla kullanılmıştır. 

### 5. **Data Transfer Object (DTO) Design Pattern**  
- **Amaç**: Verilerin uygulamanın farklı katmanları arasında taşınmasını kolaylaştırmak ve veri taşımayı optimize etmek.  
- **Projedeki Uygulama**:  
  - AracResponse, Arac nesnesinin kullanıcıya veya başka bir katmana uygun bir şekilde aktarılmasını sağlamak için kullanılan bir DTO'dur.
Bu DTO, Arac nesnesinin yalnızca belirli özelliklerini içerir ve servis katmanından controller katmanına veri transferi için kullanılmıştır.  

## Projenin Avantajları  
- **Yeniden Kullanılabilirlik**: Tasarım desenleri sayesinde, kodun belirli parçaları birden fazla bağlamda kullanılabilir.  
- **Modülerlik**: Kod daha küçük ve yönetilebilir birimlere bölünmüştür.  
- **Esneklik**: Nesne oluşturma ve iş akışları kolayca genişletilebilir.  
- **Bakım Kolaylığı**: Kodun anlaşılması ve güncellenmesi daha kolay hale getirilmiştir.  

## Kullanım  
Proje, aşağıdaki adımları izleyerek çalıştırılabilir:  

## Kullanılan Teknolojiler
- **Java Versiyonu:** `17.0.7`
- **Spring Boot Versiyonu:** `3.3.2`
- **MySQL Versiyonu:** `8.0.40`

Bu proje yukarıdaki sürümlerle yapılandırılmıştır. Diğer sürümlerle uyumluluk hakkında aşağıdaki önerilere başvurabilirsiniz.

## Uyumluluk Rehberi

### Java
- **Minimum Gerekli Versiyon:** `Java 17.0.0`
- **Önerilen Versiyon:** `Java 17.0.7` ve üzeri
- **Tavsiye Edilmeyenler:** `Java 16` veya daha düşük sürümler Spring Boot 3.x ile uyumlu olmayabilir. Java 18 ve daha yeni sürümler desteklenmekle birlikte ek yapılandırma değişiklikleri gerekebilir.

### Spring Boot
- **Minimum Gerekli Versiyon:** `Spring Boot 3.3.0`
- **Önerilen Versiyon:** `Spring Boot 3.3.2` ve üzeri
- **Uyumlu Versiyonlar:** `Spring Boot 3.3.x` serisi, proje yapısıyla tamamen uyumludur ve Spring Boot Security ile sorunsuz çalışır.
- **Tavsiye Edilmeyenler:** `3.2.x` ve daha düşük sürümler, bazı güvenlik özelliklerini desteklemeyebilir ve mevcut proje yapısıyla uyumsuz olabilir.

### MySQL
- **Minimum Gerekli Versiyon:** `MySQL 8.0.20`
- **Önerilen Versiyon:** `MySQL 8.0.40` ve üzeri
- **Uyumlu Versiyonlar:** `MySQL 8.0.20` ile `8.1.x` arasındaki sürümler, proje ile uyumlu olup güncel güvenlik standartlarını karşılamaktadır.
- **Tavsiye Edilmeyenler:** `MySQL 5.x` ve daha eski sürümler, projede kullanılan bazı SQL sorguları ve veri tipleriyle sorun oluşturabilir. `MySQL 8.2.x` ve üzeri, burada belirtilmeyen ek yapılandırma ayarlarını gerektirebilir.

## E-posta Konfigürasyonu

Bu proje, e-posta bildirimleri için Gmail’in SMTP sunucusunu kullanmaktadır. Aşağıdaki ayarları `application.properties` dosyasına ekleyerek e-posta gönderimi için gerekli konfigürasyonu yapabilirsiniz:

![Email Configuration](application.password.png)

- **spring.mail.host**: `smtp.gmail.com`
- **spring.mail.port**: `587`
- **spring.mail.username**: Email adresiniz (e.g., `your-email@gmail.com`)
- **spring.mail.password**: Google hesabınızın güvenlik ayarları üzerinden oluşturulan uygulamaya özel şifre.

> **Not**: Gmail için uygulamaya özel bir parola ayarlama hakkında ayrıntılı talimatlar için [bu videoya](https://www.youtube.com/watch?v=3vINS4tzjIw&list=LL&index=2) başvurabilirsiniz. Bu video, iki faktörlü kimlik doğrulamayı etkinleştirme ve uygulamaya özel bir parola oluşturma konusunda adım adım rehberlik sağlar.

#### Günlük E-posta Gönderim Limitleri
- **Kişisel Gmail Hesapları:** Gmail, günlük **500 e-posta** gönderim limiti uygulamaktadır. Bu limit, yüksek hacimli e-posta gönderen uygulamaları etkileyebilir.
- **Kurumsal Gmail Hesapları:** G Suite (Google Workspace) hesapları genellikle daha yüksek bir günlük e-posta gönderim limitine sahiptir ve abonelik seviyesine bağlı olarak günlük **2.000 e-posta** göndermeye izin verebilir.

> **Hatırlatma:** Uygulamaya özel şifre oluşturmak için Google hesabınızda iki faktörlü kimlik doğrulamayı etkinleştirmeniz gereklidir.

## Veritabanı Konfigürasyonu

Projeyi kurduktan sonra, veritabanınızdaki `roles` tablosunu aşağıdaki girdilerle yapılandırmanız gerekmektedir:

![Roles Tablosu](roles.png)

- `ROLE_USER`
- `ROLE_ADMIN`

Bu rolleri eklemek için aşağıdaki SQL sorgusunu çalıştırabilirsiniz:

```sql
INSERT INTO roles (id, name) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');
```

Bu kurulum, uygulama içerisindeki rol tabanlı erişim kontrolü için gereklidir. Rollerin eksik olması durumunda bazı işlevler beklenildiği gibi çalışmayabilir.







