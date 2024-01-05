import java.util.Scanner

// Lớp cha chứa thông tin chung của độc giả
open class DocGia(
    var maDocGia: String,
    var hoTen: String,
    var ngayHetHan: String,
    var gioiTinh: String
) {
    // Hàm nhập thông tin độc giả
    open fun nhapThongTin() {
        val scanner = Scanner(System.`in`)
        println("Nhập thông tin độc giả:")
        print("Mã độc giả: ")
        maDocGia = scanner.nextLine()
        print("Họ tên: ")
        hoTen = scanner.nextLine()
        print("Ngày hết hạn: ")
        ngayHetHan = scanner.nextLine()
        print("Giới tính: ")
        gioiTinh = scanner.nextLine()
    }

    // Hàm xuất thông tin độc giả
    open fun xuatThongTin() {
        println("Thông tin độc giả:")
        println("Mã độc giả: $maDocGia")
        println("Họ tên: $hoTen")
        println("Ngày hết hạn: $ngayHetHan")
        println("Giới tính: $gioiTinh")
    }
}

// Lớp con kế thừa từ lớp cha, đại diện cho độc giả thường
class DocGiaThuong(
    maDocGia: String,
    hoTen: String,
    ngayHetHan: String,
    gioiTinh: String,
    var soSachMuonTrongThang: Int
) : DocGia(maDocGia, hoTen, ngayHetHan, gioiTinh) {

    // Hàm nhập thông tin độc giả thường
    override fun nhapThongTin() {
        super.nhapThongTin()
        val scanner = Scanner(System.`in`)
        print("Số sách mượn trong tháng: ")
        soSachMuonTrongThang = scanner.nextInt()
    }

    // Hàm xuất thông tin độc giả thường
    override fun xuatThongTin() {
        super.xuatThongTin()
        println("Số sách mượn trong tháng: $soSachMuonTrongThang")
    }

    // Hàm tính lệ phí của độc giả thường
    fun tinhLePhi(): Double {
        return soSachMuonTrongThang * 5000.0
    }

    // 5 constructors cho lớp DocGiaThuong
    constructor() : this("", "", "", "", 0)
    constructor(maDocGia: String, hoTen: String) : this(maDocGia, hoTen, "", "", 0)
    constructor(maDocGia: String, hoTen: String, soSachMuonTrongThang: Int) : this(maDocGia, hoTen, "", "", soSachMuonTrongThang)
    constructor(maDocGia: String, hoTen: String, ngayHetHan: String, gioiTinh: String) : this(maDocGia, hoTen, ngayHetHan, gioiTinh, 0)
}

// Lớp con kế thừa từ lớp cha, đại diện cho độc giả VIP
class DocGiaVIP private constructor(
    maDocGia: String,
    hoTen: String,
    ngayHetHan: String,
    gioiTinh: String
) : DocGia(maDocGia, hoTen, ngayHetHan, gioiTinh) {

    // Hàm tính lệ phí của độc giả VIP
    fun tinhLePhi(): Double {
        return 50000.0
    }

    companion object {
        // Factory method để tạo instance của DocGiaVIP
        fun createInstance(maDocGia: String, hoTen: String, ngayHetHan: String, gioiTinh: String): DocGiaVIP {
            return DocGiaVIP(maDocGia, hoTen, ngayHetHan, gioiTinh)
        }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    var choice: Int

    // Danh sách độc giả
    val docGiaThuong = DocGiaThuong()
    val docGiaVIP = DocGiaVIP.createInstance("", "", "", "")

    do {
        println("\n----- MENU -----")
        println("1. Nhập thông tin độc giả thường")
        println("2. Nhập thông tin độc giả VIP")
        println("3. Xuất thông tin độc giả thường")
        println("4. Xuất thông tin độc giả VIP")
        println("5. Tính lệ phí độc giả thường")
        println("6. Tính lệ phí độc giả VIP")
        println("0. Thoát")

        print("Chọn chức năng (0-6): ")
        choice = scanner.nextInt()

        when (choice) {
            1 -> {
                docGiaThuong.nhapThongTin()
                println("Thông tin độc giả thường đã được nhập.")
            }
            2 -> {
                docGiaVIP.nhapThongTin()
                println("Thông tin độc giả VIP đã được nhập.")
            }
            3 -> {
                docGiaThuong.xuatThongTin()
            }
            4 -> {
                docGiaVIP.xuatThongTin()
            }
            5 -> {
                println("Lệ phí độc giả thường: ${docGiaThuong.tinhLePhi()} VND")
            }
            6 -> {
                println("Lệ phí độc giả VIP: ${docGiaVIP.tinhLePhi()} VND")
            }
            0 -> println("Ứng dụng kết thúc.")
            else -> println("Chức năng không hợp lệ. Vui lòng chọn lại.")
        }

    } while (choice != 0)
}
