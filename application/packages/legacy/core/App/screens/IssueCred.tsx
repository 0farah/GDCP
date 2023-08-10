import React from 'react'
import { View, Text, TouchableHighlight, Alert, Button, StyleSheet } from 'react-native'
import { CredentialStackParams, Screens } from '../types/navigators'
import { StackNavigationProp } from '@react-navigation/stack'
import { useNavigation } from '@react-navigation/core'


const IssueCred: React.FC = () => {

    const navigation = useNavigation<StackNavigationProp<CredentialStackParams>>()
    const handleButtonPress1 = () => {
    navigation.navigate(Screens.AppelAPI);
    }
    const handleButtonPress2 = () => {
        navigation.navigate(Screens.AppelAPI2);
        }
    const styles = StyleSheet.create({
        container: {
            flexDirection: 'row',
            justifyContent: 'center',
            marginTop: 20,
          },
        button: {
            backgroundColor: 'white',
            paddingHorizontal: 40,
            paddingVertical: 10,
            borderRadius: 5,
            marginHorizontal: 10,
        },
        promptText: {
            marginBottom: 10,
            fontSize: 16,
            color: 'white',
          },
      })

  return (
    <View>
        <Text style={styles.promptText}>As part of our ongoing medical research, the Investigator Center is seeking individuals who are willing to participate in our study. Your contribution would be valuable in helping us advance medical knowledge and improve patient care.

We kindly invite you to consider participating in our research study. By joining our study, you will have the opportunity to contribute to important scientific discoveries and potentially benefit from new medical treatments and interventions.

Participation in the study will involve various assessments, tests, and data collection related to your medical condition. Your privacy and confidentiality will be strictly maintained throughout the research process, and all data will be anonymized to ensure confidentiality.

If you are interested in participating or would like to learn more about the study, please contact us at [contact information]. Our research team will be happy to provide you with detailed information and answer any questions or concerns you may have.

Your participation in this research could make a significant impact on the future of medical treatments and the well-being of patients. We sincerely appreciate your consideration and look forward to your involvement in our study.

Thank you for your time and support.

Sincerely,
[Investigator Center Name]



</Text>
    <View>
        <Button title="Accept" onPress={handleButtonPress1} />
         {/* <TouchableHighlight  onPress={() => Alert.alert("You have succesfully accepted the Research")}>
                <Text style={styles.button}>accept</Text>
  </TouchableHighlight> */}
    </View>

        <View>
        <Button title="Deny" onPress={handleButtonPress2} />
            {/* <TouchableHighlight  onPress={() => Alert.alert("You have denied this research")}>
                <Text style={styles.button}>deny</Text>
</TouchableHighlight> */}
        </View>
    </View>
  )
}

export default IssueCred
