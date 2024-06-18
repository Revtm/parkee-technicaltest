"use server";
export async function onCheckIn(prevState, formData) {
    let plateNumber = formData.get("plate_number");
    const response = await fetch("http://localhost:8080/pos/checkin",
        {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({plateNumber: plateNumber}),
            cache : "no-store"
        }
    ).catch(e => {
        return new Response(JSON.stringify({status: 500, data: {message: "Terdapat kesalahan sistem"}}), {status: 500});
    });

    let body = await response.json();

    if(!body){
        return null;
    }

    return body;
}

export async function onCheckOut(prevState, formData) {
    let plateNumber = formData.get("plate_number");

    const response = await fetch("http://localhost:8080/pos/checkout",
        {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({plateNumber: plateNumber}),
            cache : "no-store"
        }
    ).catch(e => {
        return new Response(JSON.stringify({status: 500, data: {message: "Terdapat kesalahan sistem"}}), {status: 500});
    });

    let body = await response.json();

    if(!body){
        return null;
    }

    return body;
}

export async function onPay(prevState, formData) {
    let plateNumber = formData.get("plate_number");

    const response = await fetch("http://localhost:8080/pos/payment",
        {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({plateNumber: plateNumber}),
            cache : "no-store"
        }
    ).catch(e => {
        return new Response(JSON.stringify({status: 500, data: {message: "Terdapat kesalahan sistem"}}), {status: 500});
    });

    let body = await response.json();

    if(!body){
        return null;
    }

    return body;
}