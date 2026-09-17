import React from 'react'
import { Route, Routes } from 'react-router-dom'
import BusinessTable from '../Admin/pages/business/BusinessTable'



const AdminRoutes = () => {
  return (
    <Routes>
    <Route path='/' element={<BusinessTable/>}/>
    
  </Routes>
  )
}

export default AdminRoutes
