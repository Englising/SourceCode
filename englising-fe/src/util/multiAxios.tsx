import axios from "axios";

const instance = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
});

const getMultiplayUsers = (multiplayId: string) => {
  return instance.get(`/multiplay/${multiplayId}`);
};

const getMultiplayResult = (multiplayId: string) => {
  return instance.get(`/multiplay/${multiplayId}/result`);
};

const getMultiplayInfo = (multiplayId: string) => {
  return instance.get(`/multiplay/${multiplayId}`);
};

export { getMultiplayUsers, getMultiplayResult, getMultiplayInfo };
