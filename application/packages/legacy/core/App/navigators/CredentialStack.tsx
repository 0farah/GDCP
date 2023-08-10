import { createStackNavigator } from '@react-navigation/stack'
import React from 'react'
import { useTranslation } from 'react-i18next'

import SettingsMenu from '../components/buttons/SettingsMenu'
import { useConfiguration } from '../contexts/configuration'
import { useTheme } from '../contexts/theme'
import CredentialDetails from '../screens/CredentialDetails'
import ListCredentials from '../screens/ListCredentials'
import { CredentialStackParams, Screens } from '../types/navigators'

import { createDefaultStackOptions } from './defaultStackOptions'
import IssueCred from '../screens/IssueCred'
import AppelAPI from '../screens/AppelAPI'

const CredentialStack: React.FC = () => {
  const Stack = createStackNavigator<CredentialStackParams>()
  const theme = useTheme()
  const { t } = useTranslation()
  const { credentialListHeaderRight: CredentialListHeaderRight } = useConfiguration()
  const defaultStackOptions = createDefaultStackOptions(theme)

  return (
    <Stack.Navigator screenOptions={{ ...defaultStackOptions }}>
      <Stack.Screen
        name={Screens.Credentials}
        component={ListCredentials}
        options={() => ({
          title: t('Screens.Credentials'),
          headerRight: () => <CredentialListHeaderRight />,
          headerLeft: () => <SettingsMenu />,
        })}
      />
      <Stack.Screen
        name={Screens.IssueCred}
        component={IssueCred}
        options={{ title: 'Issue Credential' }}
      />
      <Stack.Screen
        name={Screens.AppelAPI}
        component={AppelAPI}
        options={{ title: 'AppelAPI' }}
      />
      <Stack.Screen
        name={Screens.CredentialDetails}
        component={CredentialDetails}
        options={{ title: 'Screens.CredentialDetails' }}
      />
      
    </Stack.Navigator>
  )
}

export default CredentialStack
