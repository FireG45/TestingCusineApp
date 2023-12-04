import { Route, BrowserRouter, Routes } from 'react-router-dom';
import TestList from './components/TestList';
import PrimarySearchAppBar from './components/PrimarySearchAppBar';
import React from 'react';
import UploadTest from './components/UploadTest';

function App() {
  return (
    <React.StrictMode>
      <BrowserRouter>
      <PrimarySearchAppBar/>
        <Routes>
          <Route path="/" exact element={<TestList />} />
          <Route path="/upload" exact element={<UploadTest />} />
        </Routes>
      </BrowserRouter>
    </React.StrictMode>


  );
}

export default App;
