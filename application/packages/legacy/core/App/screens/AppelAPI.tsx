import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { StyleSheet, Text, View } from 'react-native'


const AppelAPI: React.FC = () => {
  const [post, setPost] = useState(null)
  const [error, setError] = useState(null)

  const styles = StyleSheet.create({
    container: {
      flex: 1,
      justifyContent: 'center',
      alignItems: 'center',
    },
    promptText: {
      marginBottom: 10,
      fontSize: 16,
      color: 'white',
    },

  })


  useEffect( () => {
    const baseURL = 'https://42c9-41-231-58-146.ngrok-free.app'
    const endpoint = '/issue-credential/send-offer'
    const url = baseURL + endpoint
    const data = {
      connection_id: '75bf7547-d35d-4720-b345-4998eac87fc3',
      cred_def_id: 'AQVnxQW6YNHgdadEAKfa9L:3:CL:880494:patient.agent.consent_schema',
      credential_preview: {
        '@type': 'did:sov:BzCbsNYhMrjHiqZDTUASHg;spec/issue-credential/1.0/credential-preview',
        attributes: [
          { name: 'patient_ref', value: '123123123' },
          { name: 'medical_ref', value: '1111111' },
          { name: 'consent', value: 'True' },
        ],
      },
    }
    axios
      .post(url, data)
      .then((response) => {
        setPost(response.data);
      })
      .catch((error) => {
        setError(error);
      });
    })
  return (
    <View style={styles.container}>
      <Text style={styles.promptText}>
        You have successfully accepted to be part in this research.
        {'\n'}
        Congratulations!
    </Text>
      {post && <Text>Response: {JSON.stringify(post)}</Text>}
      {error && <Text>Error: {error.message}</Text>}
    </View>
  )
}

export default AppelAPI
