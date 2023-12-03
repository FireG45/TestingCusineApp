import { Route, BrowserRouter, Routes } from 'react-router-dom';
import TestList from './components/TestList';
import PrimarySearchAppBar from './components/PrimarySearchAppBar';
import React from 'react';

function App() {
  return (
    <React.StrictMode>
      <BrowserRouter>
      <PrimarySearchAppBar/>
        <Routes>
          <Route path="/" exact element={<TestList />} />
        </Routes>
      </BrowserRouter>
    </React.StrictMode>


  );
}

export default App;
