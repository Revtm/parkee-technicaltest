"use client"
import Image from "next/image";
import Link from "next/link";
import {useFormState} from "react-dom";
import {onCheckOut, onPay} from "@/app/_lib/action";

export default function CheckOutPage() {
    const [state, formAction] = useFormState(onCheckOut, {status:'', data: {
            plateNumber:'',
            checkInTime: '',
            checkOutTime: '',
            totalPrice: 0,
            parkingStatus: '',
            message: ''
    }});

    const [statePay, formActionPay] = useFormState(onPay, {status:'', data: {
            plateNumber:'',
            message:''
        }});

    return (
        <div>
            <div className="mx-10 mt-10 flex flex-row gap-4">
                <div className="form basis-2/3 p-3 rounded-md flex flex-col gap-5">
                    <div className="border rounded-md flex flex-row gap-3">
                        <div className="basis-1/2 border rounded-md">
                            <center>
                                <Image src="/contoh-plat.jpg" alt="contoh plat" height={200} width={400}></Image>
                            </center>
                            <p className="bg-gray-100 text-sm px-1"> Exit Camera</p>
                        </div>
                        <div className="basis-1/2 border rounded-md">
                            <center>
                                <Image src="/contoh-plat.jpg" alt="contoh plat" height={200} width={400}></Image>
                            </center>
                            <p className="bg-gray-100 text-sm px-1"> Entry Camera</p>
                        </div>
                    </div>
                    <form action={formAction}>
                        <div className="w-full flex flex-row gap-3">
                            <input type="text" name="plate_number" placeholder="Input Plat Nomor Kendaraan" className="form-input w-full px-4 py-3 rounded-md basis-5/6"/>
                            <button onClick={(e) => {state.status = ""; statePay.status = "";}} type="submit" className="basis-1/6 p-2 w-full bg-red-600 text-white text-sm rounded-xl">{"Check Out"}</button>
                        </div>
                    </form>
                </div>
                <div className="ticket-detail basis-1/3 p-3 rounded-md flex flex-col">
                    <div className="basis-5/6 title text-2xl">
                        <div className="text-center">Check Out - Pintu Keluar</div>
                        <div className="flex flex-row mt-1">
                            <div className="basis-1/3 text-sm">No. Plat</div>
                            <div className="basis-2/3 text-sm">: <span>{state.status === "SUCCESS" ? state.data.plateNumber : ''}</span></div>
                        </div>
                        <div className="flex flex-row mt-1">
                            <div className="basis-1/3 text-sm">Waktu Check-In</div>
                            <div className="basis-2/3 text-sm">: <span>{state.status === "SUCCESS" ? state.data.checkInTime : ''}</span></div>
                        </div>
                        <div className="flex flex-row mt-1">
                            <div className="basis-1/3 text-sm">Waktu Check-Out</div>
                            <div className="basis-2/3 text-sm">: <span>{state.status === "SUCCESS" ? state.data.checkOutTime : ''}</span></div>
                        </div>
                    </div>
                    <div className="basis-1/6 relative">
                        <div className="flex flex-row mb-2">
                            <div className="basis-1/3 font-bold">Total Price</div>
                            <div className="basis-2/3 font-bold">: <span> Rp{state.status === "SUCCESS" ? state.data.totalPrice : ''}</span></div>
                        </div>
                        <div>
                            <form action={formActionPay}>
                                <input type="hidden" name="plate_number" value={state.data.plateNumber ? state.data.plateNumber : (statePay.data.plateNumber ? statePay.data.plateNumber :'')}/>
                                <button onClick={(e) => {state.status = ""; statePay.status = "";}} type="submit" className="absolute inset-x-0 bottom-0 p-2 w-full bg-red-600 text-white text-sm rounded-xl">Bayar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div className="mx-10 mt-2 flex flex-row gap-4">
                <div className="message rounded-md basis-1/2 p-2">
                    <div className="nav flex flex-row gap-3">
                        <Link href="/" className="text-red-500 underline">Home</Link>
                        <Link href="/checkin" className="text-red-500 underline">Check-In</Link>
                        <Link href="/checkout" className="text-red-500 underline">Check-Out</Link>
                    </div>
                </div>
                <div className="message rounded-md basis-1/2 p-2">
                    Pesan :<span className="px-1" style={state.status === "SUCCESS" || statePay.status === "SUCCESS" ? {color:"green"} : {color:"red"}}>
                        {statePay.status === '' ? (state.status !== '' ? state.data.message : '') : (statePay.status !== '' ? statePay.data.message : '')}
                    </span>
                </div>
            </div>
        </div>
    );
}