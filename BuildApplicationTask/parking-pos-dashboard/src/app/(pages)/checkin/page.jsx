"use client"
import Image from "next/image";
import Link from "next/link";
import {onCheckIn} from "@/app/_lib/action";
import {useFormState} from "react-dom";

export default function CheckInPage() {
    const [state, formAction] = useFormState(onCheckIn, {status:'', data: {plateNumber:'', checkInTime:'', message:''}});

    return (
        <div>
            <form action={formAction}>
            <div className="mx-10 mt-10 flex flex-row gap-4">

                    <div className="form basis-2/3 p-3 rounded-md flex flex-col gap-5">
                        <div className="border rounded-md">
                            <center>
                                <Image src="/contoh-plat.jpg" alt="contoh plat" height={348} width={485}></Image>
                            </center>
                            <p className="bg-gray-100 text-sm px-1"> Entry Camera</p>
                        </div>
                        <div>
                            <input type="text" name='plate_number' placeholder="Input Plat Nomor Kendaraan" className="form-input w-full px-4 py-3 rounded-md"/>
                        </div>
                    </div>
                    <div className="ticket-detail basis-1/3 p-3 rounded-md flex flex-col">
                        <div className="basis-5/6 title text-2xl">
                            <div className="text-center">Check In - Pintu Masuk</div>
                            <div className="flex flex-row mt-1">
                                <div className="basis-1/3 text-sm">No. Plat</div>
                                <div className="basis-2/3 text-sm">: <span>{state.status === "SUCCESS" ? state.data.plateNumber : ''}</span></div>
                            </div>
                            <div className="flex flex-row mt-1">
                                <div className="basis-1/3 text-sm">Waktu Check-In</div>
                                <div className="basis-2/3 text-sm">: <span>{state.status === "SUCCESS" ? state.data.checkInTime : ''}</span></div>
                            </div>
                        </div>
                        <div className="basis-1/6 relative">
                            <button type="submit" className="absolute inset-x-0 bottom-0 p-2 w-full bg-red-600 text-white text-sm rounded-xl">Cetak Tiket</button>
                        </div>
                    </div>
            </div>
            </form>
            <div className="mx-10 mt-2 flex flex-row gap-4">
                <div className="message rounded-md basis-1/2 p-2">
                    <div className="nav flex flex-row gap-3">
                        <Link href="/" className="text-red-500 underline">Home</Link>
                        <Link href="/checkin" className="text-red-500 underline">Check-In</Link>
                        <Link href="/checkout" className="text-red-500 underline">Check-Out</Link>
                    </div>
                </div>
                <div className="message rounded-md basis-1/2 p-2" >
                    Pesan :
                    <span className="px-1" style={state.status === "SUCCESS" ? {color:"green"} : {color:"red"}}>
                        {state.status !== '' ? state.data.message : ''}
                    </span>
                </div>
            </div>
        </div>
    );
}