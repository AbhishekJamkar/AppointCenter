import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import {
  addNotification,
} from "../../../Redux/Notifications/action";
import NotificationCard from "./NotificationCard";

const Notification = ({type}) => {
  const dispatch = useDispatch();
  const { auth, notification } = useSelector((store) => store);

  



  const [stompClient, setStompClient] = useState(null);

  useEffect(() => {
    const sock = new SockJS("http://localhost:5000/api/notifications/ws");
    const stomp = Stomp.over(sock);
    setStompClient(stomp);
  }, []);

  useEffect(() => {
    if (stompClient) {
      stompClient.connect(
        {},
        () => {
          if (true) {
            stompClient.subscribe(
              `/notification/user/${auth.user?.id}`,
              onMessageRecive,
              (error) => {
                console.error("Subscription error:", error);
              }
            );
            console.log("Subscribed to ====== ", `/notification/user/${auth.user?.id}`);
          }
        },
        (error) => {
          console.error("WebSocket error:", error);
        }
      );
    }

    // Cleanup function to disconnect WebSocket on unmount
    // return () => {
    //   if (stompClient?.connected) {
    //     stompClient.disconnect(() => {
    //       console.log("Disconnected from WebSocket");
    //     });
    //   }
    // };
  }, [stompClient]);

  const onMessageRecive = (payload) => {
    console.log("new message received", payload);
    const receivedNotification = JSON.parse(payload.body);
    
    console.log("new message received", receivedNotification);
    dispatch(addNotification(receivedNotification));
  };

  return (
    <div className="flex justify-center  px-5 md:px-20 py-5 md:py-10">
      <div className="space-y-5 w-full lg:w-1/2 ">
      <h1 className="text-2xl font-bold text-center">Notifications</h1>
        {notification.notifications.map((item) => (
          <NotificationCard type={type} key={item.id} item={item} />
        ))}
      </div>
    </div>
  );
};

export default Notification;
