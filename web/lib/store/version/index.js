/*
 * Copyright 2023 Datastrato.
 * This software is licensed under the Apache License version 2.
 */

import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'

import { getVersionApi } from '@/lib/api/version'

export const initialVersion = createAsyncThunk('sys/fetchVersion', async (params, { getState }) => {
  const response = await getVersionApi()

  const { version } = response.data

  sessionStorage.setItem('version', version.version)

  console.log(
    `Gravitino Version: %c${version.version}`,
    `color: white; background-color: #6062E0; padding: 2px; border-radius: 4px;`
  )

  return version
})

export const sysSlice = createSlice({
  name: 'sys',
  initialState: {
    version: null
  },
  reducers: {
    setVersion(state, action) {
      state.version = action.payload
    }
  },
  extraReducers: builder => {
    builder.addCase(initialVersion.fulfilled, (state, action) => {
      state.version = action.payload.version
    })
  }
})

export const { setVersion } = sysSlice.actions

export default sysSlice.reducer
