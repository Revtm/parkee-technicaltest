# Parkee Technical Test
Muhammad Iqbal Revantama
## Single Linked List
```java
  SingleLinkedList numbers = new SingleLinkedList();

  numbers.insertAtBeginning(3);
  numbers.insertAtEnd(5);
  numbers.insertAtEnd(7);
  numbers.insertAtBeginning(1);
  numbers.display(); // expected output : [1,3,5,7]
```

## Linux Task
### Find By File Extension
Masuk ke folder LinuxTask dan berikan izin file untuk execution dengan command  ``chmod +x searchfile.sh``.
Untuk mencari file berdasarkan extensi dapat menggunakan command ``./searchfile.sh namafolder namaextensi`` (extensi cukup nama tanpa titik)

contoh: ``./searchfile.sh file txt``

<img width="497" alt="image" src="https://github.com/Revtm/parkee-technicaltest/assets/39016040/c98961e2-4e83-43a2-a4da-ca6a1d9b188d">

### Update Package Automation

### SSH Key

## Task Build Application
Aplikasi ini terbagi menjadi dua yaitu sisi backend dan frontend. Backend dikembangkan dengan Java Spring Boot dan frontend dikembangkan dengan framework Next.js

### Front-End
Saat dikembangkan menggunakan spesifikasi sebagai berikut:
| name | version|
| :--- | :--- |
| Node | 20.11.0 |
| Npm | 10.2.4 |
| Next JS | 14.2.4 (latest) |

#### Cara Menjalankan Program
1. Download (https://nodejs.org/en) dan Install Node bila belum tersedia (Npm sudah included dengan Node.js installation)
2. Masuk ke folder ``/parking-pos-dashboard``
3. Buat file .env.development bila belum ada dan masukkan konfigurasi seperti berikut dan kemudian simpan:
   ```code
   NODE_ENV=development
   API_BASE_URL=http://localhost:8080
   ```
4. Jalankan command ``nmp run dev``, secara otomatis akan menjalankan aplikasi pada localhost port :3000
5. Aplikasi dapat digunakan setelah program backend telah dijalankan

### Back-End
Saat dikembbangkan menggunakan spesifikasi sebagai berikut:
| name | version|
| :--- | :--- |
| Java | 17 |
| Spring Boot | 3.2.6 |
| Postgres database | 12 |

#### Cara Menjalankan Program
##### Migrasi Database
Project sudah terintegrasi dengan flyway, dan akan melakukan auto migration saat program ParkingPOS pertama dijalankan
1. Install database postgresql
2. Buat database baru bernama ``parking-pos``
3. Run project ParkingPOS melalui IDE atau command line

Menggunakan SQL file
bila auto migration tidak berkerja
1. Install database postgresql
2. Buat database baru bernama ``parking-pos``
3. Export file sql dari folder ``sql-file``

##### Menjalankan Aplikasi
Melalui Command line
1. Masuk ke folder ``/ParkingPOS-Executable``
2. Update konfigurasi database di file application.properties
   ```code
    spring.application.name=ParkingPOS
    db.postgres.driver=org.postgresql.Driver
    db.postgres.url=jdbc:postgresql://localhost:5432/[nama database]
    db.postgres.username=[nama username database]
    db.postgres.password=[password database]
   ```
3. Run aplikasi dengan command ``java -jar ParkingPOS-0.0.1-SNAPSHOT.jar -Dspring.config.location=application.properties``
4. Spring boot application akan berjalan di localhost port 8080
   
Melalui IDE Intellij
1. Buka project ``/ParkingPOS`` di IDE (misal Intellij)
2. Update konfigurasi database di file application.properties
   ```code
    spring.application.name=ParkingPOS
    db.postgres.driver=org.postgresql.Driver
    db.postgres.url=jdbc:postgresql://localhost:5432/[nama database]
    db.postgres.username=[nama username database]
    db.postgres.password=[password database]
   ```
4. Build dan Run project
5. Spring boot application akan berjalan di localhost port 8080
  
