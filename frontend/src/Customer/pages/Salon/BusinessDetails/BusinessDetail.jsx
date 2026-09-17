import React from 'react'
import { useSelector } from 'react-redux'

const BusinessDetail = () => {
    const {business}=useSelector(store=>store)
  return (
    <div className="space-y-5 mb-20">
    <section className="grid grid-cols-2  gap-3">
      <div className="col-span-2">
        <img
          className="w-full rounded-md h-[15rem] object-cover"
          src={business.business?.images[0]}
          alt=""
        />
      </div>
      <div className="col-span-1">
        <img
          className="w-full  rounded-md h-[15rem] object-cover"
          src={business.business?.images[1]}
          alt=""
        />
      </div>
      <div className="col-span-1">
        <img
          className="w-full  rounded-md h-[15rem] object-cover"
          src={business.business?.images[2]}
          alt=""
        />
      </div>
    </section>


      <div className="space-y-3">
        <h1 className="font-bold text-3xl">{business.business?.name} </h1>
        <p>
          {business.business?.address}, {business.business?.city}
        </p>
        <p>
          <strong>Timing :</strong> {business.business?.openTime} To{" "}
          {business.business?.closeTime}
        </p>
      </div>
  
  </div>
  )
}

export default BusinessDetail