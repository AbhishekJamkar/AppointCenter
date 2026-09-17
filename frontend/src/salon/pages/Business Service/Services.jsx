import React, { useEffect } from 'react'
import ServicesTable from './ServicesTable'
import { useDispatch } from 'react-redux';
import { fetchBusinessByOwner } from '../../../Redux/Business/action';


const Services = () => {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchBusinessByOwner(localStorage.getItem("jwt")));
  }, []);
  
  return (
    <div>
      <ServicesTable/>
    </div>
  )
}

export default Services