import Link from "next/link";

export default function Home() {
    return (
        <div>
            <center>

                <div className="flex flex-col gap-3 mt-16">
                    <div className="text-2xl font-bold">
                        Selamat Datang
                    </div>
                    <div>
                        <Link href="/checkin" className="p-2 bg-red-600 text-white text-sm rounded-md">Halaman Check-In</Link>
                    </div>
                    <div>
                        <Link href="/checkout" className="p-2 bg-red-600 text-white text-sm rounded-md">Halaman Check-Out</Link>
                    </div>
                    <div>
                        Technical Test @ Parkee | Muhammad Iqbal Revantama
                    </div>
                </div>
            </center>
        </div>
    );
}