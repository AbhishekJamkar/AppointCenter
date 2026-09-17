import React from "react";

const Footer = () => {
  return (
    <footer class="bg-gray-900 text-gray-200 py-10 flex flex-col items-center justify-center p-20">
      <div class="container mx-auto grid grid-cols-1 md:grid-cols-3 gap-8 px-6 md:px-0">
        <div>
          <h3 class="text-xl font-semibold mb-4">About Us</h3>
          <p class="text-sm">
            Welcome to AppointCenter, your one-stop destination for premium
            business services. Book appointments with ease and experience luxury at
            your fingertips.
          </p>
        </div>

        <div>
          <h3 class="text-xl font-semibold mb-4">Quick Links</h3>
          <ul class="space-y-2 text-sm">
            <li>
              <a href="/" class="hover:text-gray-400">
                Home
              </a>
            </li>
            <li>
              <a href="/" class="hover:text-gray-400">
                Services
              </a>
            </li>
            <li>
              <a href="/" class="hover:text-gray-400">
                Book Appointment
              </a>
            </li>
            <li>
              <a href="/" class="hover:text-gray-400">
                About Us
              </a>
            </li>
            <li>
              <a href="/" class="hover:text-gray-400">
                Contact
              </a>
            </li>
          </ul>
        </div>

        <div>
          <h3 class="text-xl font-semibold mb-4">Contact Us</h3>
          <ul class="space-y-2 text-sm">
            <li>
              <i class="fas fa-phone-alt"></i> +91 8238596742
            </li>
            <li>
              <i class="fas fa-envelope"></i> support@jamkarabhishek.com
            </li>
            <li>
              <i class="fas fa-map-marker-alt"></i>17, Navrangpura Police Station Rd, opposite Navrangpura, Shrimali Society, Navrangpura, Ahmedabad, Gujarat 380009
            </li>
          </ul>
          <div class="mt-4 flex space-x-4">
            <a href="/" class="text-gray-400 hover:text-gray-200">
              <i class="fab fa-facebook-f"></i>
            </a>
            <a href="/" class="text-gray-400 hover:text-gray-200">
              <i class="fab fa-twitter"></i>
            </a>
            <a href="/" class="text-gray-400 hover:text-gray-200">
              <i class="fab fa-instagram"></i>
            </a>
            <a href="/" class="text-gray-400 hover:text-gray-200">
              <i class="fab fa-linkedin-in"></i>
            </a>
          </div>
        </div>
      </div>

      <div class="border-t border-gray-700 mt-8 pt-4 text-center text-sm">
        &copy; 2026 AppointCenter. All Rights Reserved.
      </div>
    </footer>
  );
};

export default Footer;
