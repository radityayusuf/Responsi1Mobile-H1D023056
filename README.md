**Informasi Praktikan**
Nama: Raditya Yusuf Ramadhan
NIM: H1D023056
Shift Baru: B
Shift Asal: G

**Penjelasan Alur Data**
1. Inisiasi dan Permintaan Data (View)
Saat aplikasi pertama kali dibuka, MainActivity akan dimuat.
MainActivity menginisialisasi TeamViewModel menggunakan TeamViewModelFactory. Factory ini menyuntikkan (inject) TeamRepository ke dalam ViewModel.
MainActivity kemudian memuat ProfileFragment sebagai halaman default.
MainActivity memanggil fungsi viewModel.fetchTeamDetails() untuk meminta data.

3. Pengambilan Data (ViewModel & Repository)
TeamViewModel, menggunakan viewModelScope (Coroutine), memanggil fungsi repository.getTeamDetails().
TeamRepository kemudian memanggil fungsi apiService.getTeamDetails() dari interface Retrofit.

4. Jaringan dan Keamanan (Retrofit & Interceptor)
Retrofit membuat permintaan HTTP GET ke endpoint v4/teams/59.
Sebelum permintaan dikirim, AuthInterceptor akan mencegat panggilan tersebut. Interceptor ini membaca API Key yang tersimpan aman di BuildConfig (yang mengambilnya dari local.properties) dan menambahkannya ke header permintaan sebagai X-Auth-Token.
Permintaan yang sudah aman dikirim ke server api.football-data.org.

5. Parsing Data (Model)
Server merespons dengan data JSON yang berisi detail tim, pelatih, dan daftar pemain.
Retrofit (menggunakan GSON Converter) secara otomatis mem-parsing (mengubah) data JSON ini menjadi objek data class Kotlin yang telah kita siapkan: TeamResponse, Coach, dan SquadMember.

6. Distribusi Data (LiveData)
Objek TeamResponse yang sudah di-parsing dikembalikan ke TeamViewModel.
ViewModel kemudian memperbarui nilai MutableLiveData bernama _teamDetails dengan data tersebut.

7. Penyajian di Layar (View / Fragment)
Semua fragment (ProfileFragment, CoachFragment, SquadFragment, HistoryFragment) menggunakan activityViewModels() untuk berbagi satu instance TeamViewModel yang sama dengan MainActivity.
Setiap fragment meng-observasi (mengamati) viewModel.teamDetails.
Ketika LiveData tersebut diperbarui (pada langkah 5), observer di fragment yang sedang aktif akan terpicu:
Di ProfileFragment: Observer mengambil data seperti team.name dan team.crest (dimuat dengan Glide) lalu menampilkannya di TextView dan ImageView.
Di SquadFragment: Observer mengambil team.squad (daftar pemain) dan memberikannya ke SquadAdapter untuk ditampilkan dalam RecyclerView.
Di CoachFragment: Observer mengambil team.coach dan menampilkan nama serta kebangsaannya.
Di HistoryFragment: Observer tidak memerlukan data API, ia hanya menampilkan teks statis dari strings.xml.


https://github.com/user-attachments/assets/9ca6cde5-16d1-4626-b183-44df7d1e4c91






